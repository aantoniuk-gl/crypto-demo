## About Crypto
Short Demo based on real Ad-Exchange workflow shows capability to create micro-payment operations.
There are several participants: 
- Publisher  - 
- Ad-Server  - 
- Two DSPs   - 
- Monitoring -  

The current version of code has been tested with [Kovan](https://gitter.im/kovan-testnet/) testnet only for now. However, other testnet should also work after proper configurations.
## Prerequisites and Notes
### Parity installation
- More information about parity intallation can be found https://www.parity.io/. The version of parity being used is 1.8.4-beta. 
- Run your parity service with the following command:
```
parity --geth --chain kovan --force-ui --reseal-min-period 0 --jsonrpc-cors "*" --jsonrpc-apis web3,eth,net,parity,traces,rpc,personal
```
It may take a while to get syncronized.
### Microraiden-java installation
- clone github repo
```
git clone https://github.com/aantoniuk-gl/microraiden-java.git
```
- build jar file
```
cd microraiden-java
./gradlew installDist    
``` 
- install jar into local maven repo
```
mvn install:install-file -Dfile=build/libs/microraiden-java.jar -DgroupId=org.microraiden -DartifactId=microraiden-java -Dversion=1.0.0 -Dpackaging=jar
```
## Working with the existing contracts deployed on Kovan.
We have deployed the [CustomToken](https://kovan.etherscan.io/address/0x0fc373426c87f555715e6fe673b07fe9e7f0e6e7) and [RaidenMicroTransferChannels](https://kovan.etherscan.io/address/0x5832edf9Da129Aa13fdA0fBff93379d3ED8a4a93) contracts. The ABI and addresses have been added in the configuration file `rm-ethereum.conf` in this project. 
## Demo installation
- clone github repo
```
git clone https://github.com/aantoniuk-gl/crypto-demo
```
## Start Demo
```
./start.sh
```
When demo app is started all channels between Publisher and Ad-Exchange, Ad-Exchange and DSPs will be created.
## Run Publisher URL to see Ad
```
http://localhost:8085
```
## See business logs(last 50 lines)
```
./show_log.sh
```
## Kovan Etherscan - Ethereum explorer
### Smart contract 
https://kovan.etherscan.io/address/0x5832edf9Da129Aa13fdA0fBff93379d3ED8a4a93
### Token contract 
https://kovan.etherscan.io/address/0x0fc373426c87f555715e6fe673b07fe9e7f0e6e7
### Publisher token balance
https://kovan.etherscan.io/token/0x0fc373426c87f555715e6fe673b07fe9e7f0e6e7?a=0x392f18577685f82c812107b726f8152f026d9dcc
### Ad-Exchange token balance
https://kovan.etherscan.io/token/0x0fc373426c87f555715e6fe673b07fe9e7f0e6e7?a=0xd69769e88bb98fbe92c557921fe81b61d3f580f5
### DSP1 token balance
https://kovan.etherscan.io/token/0x0fc373426c87f555715e6fe673b07fe9e7f0e6e7?a=0x9a317fa19cedcf6ca1217feffdd04eb782edd25f
### DSp2 token balance
https://kovan.etherscan.io/token/0x0fc373426c87f555715e6fe673b07fe9e7f0e6e7?a=0x5f971b6047d9291e83da6e9649dfb91925d6169e
## Stop Demo
```
./stop.sh
```
When demo is stopped all channels between Publisher and Ad-Exchange, Ad-Exchange and DSPs will be closed.
