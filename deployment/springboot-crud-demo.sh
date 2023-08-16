STATE=${1}

if [[ $STATE == "start" ]]
then
  echo "Starting springboot-crud-demo"
  docker-compose up -d
elif [[ $STATE == "stop" ]]
then
  echo "Stopping springboot-crud-demo"
  docker-compose down
elif [[ $STATE == "shell" ]]
then
  docker exec -it springboot-crud-demo bash
fi
