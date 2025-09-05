# ======================================
# ENV
# ======================================
IMAGE_NAME=service-discovery
CONTAINER_NAME=service-discovery
NETWORK=order
PORT=8761

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
# Log
# -------------------------
log_server:
	docker logs -f $(CONTAINER_NAME)

# -------------------------
# Remove Container
# -------------------------
	docker rm -f $(CONTAINER_NAME) || true
	docker network rm $(NETWORK) || true
