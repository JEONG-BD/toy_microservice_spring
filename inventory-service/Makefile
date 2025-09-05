# ======================================
# ENV
# =====================================
IMAGE_NAME=inventory-service
CONTAINER_NAME=inventory-service
PORT=60003
DB_IMAGE_NAME=mysql:8.0
DB_CONTAINER_NAME=inventory-service-db
DB_PORT=5003
DB_USER=admin
DB_PASSWORD=admin
DB_NAME=db
NETWORK=order
# -------------------------
# Build
# -------------------------
build:
	docker build -t $(IMAGE_NAME) .
# -------------------------
# Run DB (MySQL)
# -------------------------
run_db:
	@echo "Removing existing container..."
	docker rm -f $(DB_CONTAINER_NAME)
	@echo "Starting new container..."
	docker run -d --name $(DB_CONTAINER_NAME) \
		--network $(NETWORK) -p $(DB_PORT):3306 \
		-e MYSQL_DATABASE=$(DB_NAME) \
		-e MYSQL_USER=$(DB_USER) \
		-e MYSQL_ROOT_PASSWORD=$(DB_PASSWORD) \
		-e MYSQL_PASSWORD=$(DB_PASSWORD) $(DB_IMAGE_NAME)
	@echo "Container started successfully!"
# -------------------------
# Run Server
# -------------------------
run_server:
	@echo "Removing existing container..."
	docker rm -f $(CONTAINER_NAME) || true
	@echo "Starting new container..."
	docker run -d --name $(CONTAINER_NAME) \
		--network $(NETWORK) -p $(PORT):$(PORT) \
		-e SPRING_DATASOURCE_URL="jdbc:mysql://$(DB_CONTAINER_NAME):3306/$(DB_NAME)?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" \
		-e SPRING_DATASOURCE_USERNAME=$(DB_USER) \
		-e SPRING_DATASOURCE_PASSWORD=$(DB_PASSWORD) \
		-e SPRING_JPA_HIBERNATE_DDL_AUTO=update \
		-e SPRING_SQL_INIT_MODE=always \
		$(IMAGE_NAME)
	@echo "Container started successfully!"
# Run both
# -------------------------
run_all: run_db
	sleep 10
	$(MAKE) run_server

# -------------------------
# Stop
# -------------------------
stop_server:
	docker stop $(CONTAINER_NAME) || true
	docker rm $(CONTAINER_NAME) || true

stop_db:
	docker stop $(DB_CONTAINER_NAME) || true
	docker rm $(DB_CONTAINER_NAME) || true

stop_all: stop_server  stop_db

# -------------------------
# Logs
# -------------------------
logs_server:
	docker logs -f $(CONTAINER_NAME)

logs_db:
	docker logs -f $(DB_CONTAINER_NAME)

# -------------------------
# Remove Container
# -------------------------
	docker rm -f $(CONTAINER_NAME) $(DB_CONTAINER_NAME) || true
	docker network rm $(NETWORK) || true
