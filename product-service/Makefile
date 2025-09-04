# ======================================
# 환경 변수
# ======================================
IMAGE_NAME=product-service
CONTAINER_NAME=product-service
NETWORK=order
PORT=60001

DB_CONTAINER_NAME=product-service-db
DB_PORT=27017
DB_IMAGE=mongo:6.0

# ======================================
# 빌드
# ======================================
build:
	docker build -t $(IMAGE_NAME) .

# ======================================
# 앱 실행
# ======================================
run_app:
	@echo "Removing existing container..."
	docker rm -f $(CONTAINER_NAME) || true
	@echo "Starting new container..."
	docker run -d --name $(CONTAINER_NAME) --network $(NETWORK) -p $(PORT):$(PORT) $(IMAGE_NAME)
	@echo "Container started successfully!"
# ======================================
# MongoDB 실행
# ======================================
run_db:
	docker network create $(NETWORK) || true
	docker run -d --name $(DB_CONTAINER_NAME) --network $(NETWORK) -p $(DB_PORT):$(DB_PORT) $(DB_IMAGE)

# ======================================
# 로그 보기
# ======================================
log_app:
	docker logs -f $(CONTAINER_NAME)

log_db:
	docker logs -f $(DB_CONTAINER_NAME)

# ======================================
# 컨테이너 삭제
# ======================================
clean:
	docker rm -f $(CONTAINER_NAME) $(DB_CONTAINER_NAME) || true
	docker network rm $(NETWORK) || true
