# To run project

# Start db
`docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=conveyordb mysql`

If you want to use different database configurations, make sure to update conf/application.conf

# Run
```
cd conveyor-api
sbt run
```


# If you want to turn off evolutions
```aidl
play.evolutions.enabled=true
```

# Make a request

http://localhost:9000/api/scores/3
