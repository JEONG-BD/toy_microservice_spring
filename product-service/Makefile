# ======================================
# ENV
# ======================================
IMAGE_NAME=product-service
CONTAINER_NAME=product-service
NETWORK=order
PORT=60001

DB_CONTAINER_NAME=product-service-db
DB_PORT=27017
DB_IMAGE=mongo:6.0


# -------------------------
# Buildㅣ
# -------------------------
build:
	docker build -t $(IMAGE_NAME) .

# -------------------------
# Run Server
# -------------------------
run_server:
	@echo "Removing existing container..."
	docker rm -f $(CONTAINER_NAME) || true
	@echo "Starting new container..."
	docker run -d --name $(CONTAINER_NAME) --network $(NETWORK) -p $(PORT):$(PORT) $(IMAGE_NAME)
	@echo "Container started successfully!"
# -------------------------
# Run DB(MongoDB) 
# -------------------------
run_db:
	docker network create $(NETWORK) || true
	docker run -d --name $(DB_CONTAINER_NAME) --network $(NETWORK) -p $(DB_PORT):$(DB_PORT) $(DB_IMAGE)

# -------------------------
# Log
# -------------------------
log_server:
	docker logs -f $(CONTAINER_NAME)

log_db:
	docker logs -f $(DB_CONTAINER_NAME)

# -------------------------
# Remove Container 
# -------------------------
	docker rm -f $(CONTAINER_NAME) $(DB_CONTAINER_NAME) || true
	docker network rm $(NETWORK) || true
