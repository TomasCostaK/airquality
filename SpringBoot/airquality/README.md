## MySQL

### Como criar tabela em MySQL
```
CREATE TABLE `tb_city` (`id` int(11) NOT NULL AUTO_INCREMENT, `name` varchar(255) DEFAULT NULL,`aqi` int DEFAULT NULL, `pm10` int DEFAULT NULL, `pm25` int DEFAULT NULL, `dominentpol` varchar(255) DEFAULT NULL,PRIMARY KEY (`id`));
```

### Insert values into table
``` 
insert into `tb_city` (`id`, `name`, `aqi`, `pm10`, `pm25`,`dominentpol`) values('1','Aveiro','33','18','29','o3');
```

### Run sonarqube in project
mvn sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=05c275d97be7ca4039cfa00f042b2c96d1b94a07