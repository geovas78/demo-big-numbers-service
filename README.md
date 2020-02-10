## How to create a docker image

 * Build the project to create the jar file running the command after navigating to the project root:<br />
 `# .\gradlew build`
 * Make sure that Docker is up and running on your machine and execute this command from project root:<br />
 `# docker build -f Dockerfile . -t jvjordanVip/demo-numbers-service`
 
 
 you should see this:
 
 <br />
 <br />PS C:\Users\Georgi Vasilski\git\demo-big-numbers-service> docker build -f Dockerfile . -t <br />/demo-numbers-service
<br />Sending build context to Docker daemon  19.71MB
<br />Step 1/7 : FROM openjdk:8-jdk-alpine
<br /> ---> 6eb8392704ff
<br />Step 2/7 : LABEL MAINTAINER Georgi Vasilski
<br /> ---> Using cache
<br /> ---> 2bba5c7b95ec
<br />Step 3/7 : RUN set -x   && apk update && apk upgrade && apk add --no-cache ttf-dejavu fontconfig
 <br />---> Using cache
<br /> ---> e16cb03a84f8
<br />Step 4/7 : WORKDIR /opt/
 <br />---> Using cache
 <br />---> e0547f1ea54a
<br />Step 5/7 : COPY /build/libs/demo-numbers-operations-0.0.1-SNAPSHOT.jar /opt/demo.jar
 <br />---> Using cache
<br /> ---> 844a4f4d0e5c
<br />Step 6/7 : EXPOSE 7890
<br /> ---> Using cache
<br /> ---> 9fc982bd15e7
<br />Step 7/7 : CMD ["java", "-jar", "/opt/demo.jar"]
<br /> ---> Using cache
<br /> ---> 7066ba915684
<br />Successfully built 7066ba915684
<br />Successfully tagged gvasilski/demo-numbers-service:latest

* Verify the image creation with next command:<br />
`# docker images`
* Run the images and test it with Postman: <br />
`#docker run -p 7890:7890 jvjordanvip/demo-numbers-service`<br />
and try in Postman HTTP:GET @ http://localhost:7890/ping<br />
you should see a json file with two keys: status and detials. Well done!!!


## Deploy image on Kubernetes cluster

* deploy gvasilski/demo-numbers-service image on a node in the cluster running this command and give the
service a name ( I would name it here "numop-server" ):<br />
`# kubectl run numop-server --image=jvjordanvip/demo-numbers-service:latest --port 7890`
* Verify deployment with : `# kubectl get pods`
* Expose the service outside the cluster with following command : <br />
`kubectl expose deployments numop-server --type=LoadBalancer`
* Then try with Postman:<br />
- http://your-machine-ip:7890/ping
- http://your-machine-ip:7890/v1/math/inputs-multiply?number=5&multiplier=10

### Misc
If you are experiencing problems running tests in Eclipse IDE, add JUNIT5 library to the buildpath.


