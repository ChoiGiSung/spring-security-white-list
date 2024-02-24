
## Spring security 5.6
[spring-security-ip](https://gisungcu.tistory.com/551)https://gisungcu.tistory.com/551

This repository is compatible with Spring Security when using a whitelist implementation.   
The [annotation](https://github.com/ChoiGiSung/spring-security-ip/tree/main/annotation) and [voter](https://github.com/ChoiGiSung/spring-security-ip/tree/main/voter) modules are available starting from Spring Security version 5.6.  
The reason is that they follow the implementation before the change in method security.

## Spring security 6.1
The [interceptor](https://github.com/ChoiGiSung/spring-security-ip/tree/main/interceptor) module reflects the latest architecture of Spring Security 6.1.
The implementation of the whitelist has also been slightly modified. Unlike before, 
it now performs authorization checks using header tokens instead of IP whitelists.

Since relying solely on IP for authentication can pose [security issues](https://stackoverflow.com/questions/78033214/is-it-secure-to-authenticate-solely-based-on-spring-securitys-hasipaddress-conf), 
the implementation has been changed.
