# Users Service
This service manages users and user-related data as part of the [Parking project](https://github.com/AlexCyclone/parking-compose-demo).

### Guides

This application uses **profiling**:
- `local` - to run the application locally
- `secret` - provides the credentials required to run the application (use [`application-secret.yaml.example`](https://github.com/AlexCyclone/parking-user-service/blob/main/application-secret.yaml.example))
- `kafka-mode` - activates Kafka messaging integration

Swagger ui - `http://{app-host}/swagger-ui/index.html`

OpenApi Specification - `http://{app-host}/api-docs`

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* postgres: [`postgres:16.3-alpine`](https://hub.docker.com/_/postgres)
* zookeeper: [`confluentinc/cp-zookeeper:7.6.1`](https://hub.docker.com/r/confluentinc/cp-zookeeper)
* kafka: [`confluentinc/cp-kafka:7.6.1`](https://hub.docker.com/r/confluentinc/cp-kafka)

### Environment Variables
- `ENV_POSTGRES_HOST` - Postgres hostname
- `ENV_EUREKA_HOST` - Eureka server hostname
- `ENV_KAFKA_BOOTSTRAP_HOST` - Kafka host
- `ENV_JWT_EXPIRATION` - JWT expiration time seconds (e.g. 3600)
- `ENV_AWS_REGION` - AWS region (e.g. eu-central-1)
- `ENV_AWS_BUCKET` - AWS S3 bucket (e.g. icu-cyclone-cars-public)

#### Secrets:
- `ENV_POSTGRES_USERNAME` - Postgres username
- `ENV_POSTGRES_PASSWORD` - Postgres bucket
- `ENV_JWT_SECRET`: JWT secret longer than 256 bit
- `ENV_AWS_ACCESS`: AWS access key
- `ENV_AWS_SECRET`: AWS secret
