This program uses GNU LGPL.
Copyright © Abdul Habra 2011
ahabra@yahoo.com


INTRODUCTION
------------
Definition of reverse proxy (from Wikipedia):
"A reverse proxy is a type of proxy server that retrieves resources on behalf of a 
client from one or more servers. These resources are then returned to the client as 
though it originated from the reverse proxy itself."

For example you can configure a reverse proxy such that when people go to
www.my_personal_domain.com the proxy will show them results from google.com

(assuming that you own my_personal_domain.com)

The program is written in Java and is deployed as a standard WAR file to any
servlet container, e.g. Tomcat.


HOW TO BUILD
------------
The code is in the "project" directory as a Maven project.
To build the project, run this command:

mvn clean package

This will produce target/rp.war
You can deploy rp.war to and Java web server


CONFIGURATION
-------------
To configure the URLs for the reverse proxy, edit the file:

project/src/main/resources/reverseProxy.properties


