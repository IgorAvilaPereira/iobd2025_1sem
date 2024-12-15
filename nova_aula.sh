#!/bin/bash  
./atualizar.sh
mkdir $1 && cd $1 && touch "README.md" && echo "# $1 <br>" >> "README.md" && cd ./../ && git add * && git commit -m $1 && git push 
# se n tiver wiki, sera preciso comentar algumas linhas do script abaixo
./atualizar.sh 