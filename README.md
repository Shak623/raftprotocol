# RaftProtocol
This project implements and demonstrates the Raft Protocol for distributed systems.

Follow [this](http://thesecretlivesofdata.com/raft/) link for a visual demonstration of the Raft 
Protocol.


## Quick Start
Watch how the nodes behave on the command line while following the steps below:
1. Open shell and run the nodes:  
```docker-compose -f docker-compose-raftprotocol-nodes.yml up --build```

2. Once the build finishes, connect a client in a new shell tab:  
```docker-compose -f docker-compose-raftprotocol-client.yml run client```

3. Use any commands from the client interface:
  - Create or update a key-value pair: `set Value1 100`
  - Retrieve the value: `get Value1`
  - Delete the value: `del Value1`
  - Quit the client: `q`

4. When nodes are updated, they save their values to a file for persistence. Check node N's cache (if N is online):  
```docker exec -it nodeN /bin/bash```

5. In a new tab, bring node N offline:  
```docker stop nodeN```

6. Watch the reelection occur. Try submitting another value on the client.

7. Start node N again:  
```docker start nodeN```

8. The node will receive an updated cache.

## Docker
Build image with:
```
docker build -t raft-node:latest -f Dockerfile-raftprotocol
```

See the new image with:
```
docker images
```

Start a container:
```
docker run -it raft-node /bin/bash
```

##Docker-compose
Start 5 docker containers each running RaftNode:
```
docker-compose -f docker-compose-raftprotocol-nodes.yml up
```
use `--build` to rebuild docker image

Start up a client:
```
docker-compose -f docker-compose-raftprotocol-client.yml run --rm client
```

To attach the shell to a running container:
```
docker exec -it <CONTAINER-NAME> bash
```

where `<CONTAINER-NAME>` should be replaced with the name of the targeted container.
