
 need to add how to install git utility.gti
 1. To install IntellijIdea make sure that you already have JDK on your workstation.
 Run `java --version` in terminal for it.
 If Java 8 or later installed - move to step 3
 2.  to install JDK:
  - Mac: if you have HomeBrew, run `brew cask install java` in terminal. To install HomeBrew run 
    `/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"` in terminal
  - Windows: 
        a) navigate https://www.oracle.com/java/technologies/javase-downloads.html and download JDK 
        b) run downloaded file as Administrator, install JDK. Leave all installation settings default
        c) set the value of the environment variable to your JDK installation path as follows:
           run `where java` , copy the result and run
           run `setx -m JAVA_HOME "_result_"` 
           if the path contains spaces, use the shortened path name:
           for Program Files use 'Progra~1', for Program Files x86 use 'Progra~2'
           restart your computer 
           run `echo %JAVA_HOME%` in terminal. You should see the path to your JDK installation. 
 3. Navigate to https://www.jetbrains.com/idea/download/ and download 'Community' version of IntellijIdea
 4. Run downloaded file and install IntellijIdea.
  
       
 