<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <h1 style="color: blue">DWBH Insurance  Inc.</h1>
        <i>Don't Worry Be Happy</i>
        <h2>Service catalog</h2>
        <h3>Information Services</h3>
        <ol>
            <li><a href="/WebServiceProvider/LookupWebService?WSDL">LookupService</a></li>
            <li><a href="/WebServiceProvider/PersonIdentificationWebService?WSDL">PersonIdentificationService</a></li>                        
            <li><a href="/WebServiceProvider/ClaimIdentificationWebService?WSDL">ClaimIdentificationService</a></li>                        
        </ol>
        <h3>Activity Services</h3>
        <ol>            
            <li><a href="/WebServiceProvider/PersonActivityWebService?WSDL">PersonActivityService</a></li>                        
            <li><a href="/WebServiceProvider/ClaimActivityWebService?WSDL">ClaimActivityService</a></li>                        
        </ol>        
        <h3>Processes</h3>
        <ol>            
            <li><a href="/WebServiceProvider/ReportClaimProcessWebService?wsdl">ReportClaimProcess</a></li>                                    
        </ol>                
    </body>
</html>
