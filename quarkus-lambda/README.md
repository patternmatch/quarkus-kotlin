# quarkus-lambda

Build native image

```
> ./gradlew buildNative --docker-build=true
```

Build lambda package

```
> ./gradlew lambdaNativeZip
```

Deploy to AWS

```
> sls deploy
```
