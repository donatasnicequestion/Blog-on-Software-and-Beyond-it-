JavaOne 2012 - CON5020 - Using JSR 303, Bean Validation, with the Common Data Model in SOA 

Sample NetBeans 7.2 workspace (projects)

Steps to build and run a demo application:

1. Open a workspace in NetBeans (Ver 7.2, Java EE6 + Glassfish)

2. Build service provider (clean and build). 

Note: a snippet of log output in case of success:

...
------------------------------------------------------------------------
Reactor Summary:

ServiceModel ...................................... SUCCESS [4.465s]
ServiceStubs ...................................... SUCCESS [0.832s]
WebServiceProvider ................................ SUCCESS [1.847s]
BuildServiceProvider .............................. SUCCESS [0.008s]
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
...

3. Run WebServiceProvider 

Note: a sample page providing a list of services should be displayed as a result at
http://localhost:8080/WebServiceProvider/

4. Build service consumer 

Note: wwsimport maven task generates jax-ws client for a service endpoints configured in pom.xml and
running on a http://localhost:8080. Please make sure, that a WebServiceProvider ist up and running.)

Note: a snippet of log output in case of success:
...
------------------------------------------------------------------------
Reactor Summary:

WebServiceProxy ................................... SUCCESS [8.242s]
WebApp ............................................ SUCCESS [1.910s]
BuildServiceConsumer .............................. SUCCESS [0.000s]
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------

5. Run WebApp

Note: a sample web application should be displayed as a result. 
