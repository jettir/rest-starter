# Use latest jboss/base-jdk:8 image as the base
FROM jboss/base-jdk:8

# Set the WILDFLY_VERSION env variable
ENV WILDFLY_VERSION 10.1.0.Final
ENV WILDFLY_SHA1 9ee3c0255e2e6007d502223916cefad2a1a5e333
ENV JBOSS_HOME /opt/jboss/wildfly

ENV POSTGRES_JDBC_VERSION 42.1.4
ENV DB_NAME kieserver
ENV DB_USER jettir
ENV DB_PASSWORD Casa2105
ENV DB_HOST kie-server.comruotta6sj.us-east-1.rds.amazonaws.com
ENV DB_PORT 5432
ENV DATASOURCE_NAME PostgresDS
ENV PROXY_ADDRESS_FORWARDING true



USER root

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN cd $HOME \
    && curl -O https://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz \
    && sha1sum wildfly-$WILDFLY_VERSION.tar.gz | grep $WILDFLY_SHA1 \
    && tar xf wildfly-$WILDFLY_VERSION.tar.gz \
    && mv $HOME/wildfly-$WILDFLY_VERSION $JBOSS_HOME \
    && rm wildfly-$WILDFLY_VERSION.tar.gz \
    && chown -R jboss:0 ${JBOSS_HOME} \
    && chmod -R g+rw ${JBOSS_HOME}

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true


#################################################################################
# Dockerfile that provides the image for JBoss KIE Server 7.2.0.Final
#################################################################################

ENV JBOSS_BIND_ADDRESS 0.0.0.0
ENV KIE_REPOSITORY https://repository.jboss.org/nexus/content/groups/public-jboss
ENV KIE_VERSION 7.1.0.Final
ENV KIE_CLASSIFIER wildfly10
ENV KIE_CONTEXT_PATH kie-wb
ENV KIE_SERVER_PROFILE standalone
ENV KIE_DEMO false
ENV JAVA_OPTS -Xms256m -Xmx512m -Djava.net.preferIPv4Stack=true


RUN git clone https://github.com/jettir/rest-starter.git


###### SWITCH USER root ######
USER root

###### INSTALL PACKAGES ######
RUN yum install -y net-tools

##### SWITCH BACK TO jboss ####
USER jboss
####### KIE-SERVER ############


#RUN curl -o $HOME/$KIE_CONTEXT_PATH.war $KIE_REPOSITORY/org/kie/kie-wb/$KIE_VERSION/kie-wb-$KIE_VERSION-$KIE_CLASSIFIER.war && \
#unzip -q $HOME/$KIE_CONTEXT_PATH.war -d $JBOSS_HOME/standalone/deployments/$KIE_CONTEXT_PATH.war &&  \
#touch $JBOSS_HOME/standalone/deployments/$KIE_CONTEXT_PATH.war.dodeploy &&  \
#rm -rf $HOME/$KIE_CONTEXT_PATH.war




ADD start_kie-server.sh $JBOSS_HOME/bin/start_kie-server.sh


#Hay que eleiminar este archivo para que lo genere de nuevo, si no se hace larga error.
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current

#Enabling Proxy address forwarding so we can correctly handle SSL termination in front ends
#such as an OpenShift Router or Apache Proxy
RUN sed -i -e 's/<http-listener /& proxy-address-forwarding="${env.PROXY_ADDRESS_FORWARDING}" /' $JBOSS_HOME/standalone/configuration/standalone.xml


# Expose the ports we're interested in
EXPOSE 8080


RUN $JBOSS_HOME/bin/add-user.sh -u kieserver -p kieserver1! -g kie-server,admin -a -s


#CMD ["/opt/jboss/wildfly/bin/start_kie-server.sh"]

