Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
[2m2023-06-06T15:44:30.700+02:00[0;39m [31mERROR[0;39m [35m56100[0;39m [2m---[0;39m [2m[           main][0;39m [36mo.s.b.d.LoggingFailureAnalysisReporter  [0;39m [2m:[0;39m 

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).
