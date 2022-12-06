## 1. RUN FILE
Executable file in bin folder\
`bin\loginUI` use as a peer\
`bin\server` run as a server to show other peer in the network\
Note: run server in terminal `java -jar server.jar`

## 2. COMPILE FILE
### 2.1.1. Compile and run file
`if($?){javac server.java}; if($?){java server}; del *.class` \
`if($?){javac loginUI.java}; if($?){java loginUI}; del *.class`
### 2.1.2. Convert .class file to .exe file
`jar cvf server.jar *` \
`jar cvf loginUI.jar *` \
Note: you should add `Main-class: server` in server.jar as well as `Main-class: loginUI` in loginUI.jar in MANIFEST.MD file

### 2.1.3. Clean the Object file
`del *.class`

## 3. HOW IT WORKS

updating...
