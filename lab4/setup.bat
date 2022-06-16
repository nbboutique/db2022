@REM Init frontend
cd ./frontend
npm install
npm start

@REM Init backend
cd ../backend
npm install
npm start
cd ../

@REM Run docker
docker-compose up
