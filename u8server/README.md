please confirm JAVA_HOME is above 1.8
mvn install:install-file -Dfile=lenovo_pay_sign-1.0.0.5.jar -DgroupId=com.lenovo -DartifactId=lenovo_pay_sign -Dversion=1.0.0.5 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=oppo_oauth_1.0.jar -DgroupId=com.nearme -DartifactId=oppo_oauth -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=lewanPay.jar -DgroupId=com.lewan -DartifactId=lewanPay -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=signature.jar -DgroupId=com.signature -DartifactId=signature -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true