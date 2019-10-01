# quarkus-jpa

Build native image

```
> ./gradlew buildNative --docker-build=true
```

Start h2 in standalone mode as quarkus has problems with native binaries run in-memory database:

```
> java -jar h2-1.4.197.jar -webAllowOthers -tcpAllowOthers
```
