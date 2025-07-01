#!/bin/bash

# ----------------------------
# Gera INSERTs SQL com base nas colunas e nome da tabela fornecidas
# ----------------------------

echo "Informe o nome da tabela:"
read tabela

echo "Informe os nomes das colunas separados por vírgula (ex: nome,email,idade):"
read colunas

IFS=',' read -ra col_array <<< "$colunas"
num_colunas=${#col_array[@]}

echo "Quantos INSERTs deseja gerar?"
read total

echo ""
echo "-- Inserts gerados para a tabela: $tabela"
for ((i=1; i<=total; i++)); do
    valores=""
    for col in "${col_array[@]}"; do
        # Valor de preenchimento fake por tipo
        if [[ "$col" == *"id"* ]]; then
            valor=$((1000 + RANDOM % 1000))
        elif [[ "$col" == *"email"* ]]; then
            valor="'usuario${i}@exemplo.com'"
        elif [[ "$col" == *"data"* || "$col" == *"date"* ]]; then
            valor="'2025-07-0$((1 + RANDOM % 9))'"
        elif [[ "$col" == *"preco"* || "$col" == *"valor"* ]]; then
            valor=$(echo "scale=2; 10 + ($RANDOM % 100)" | bc)
        else
            valor="'Valor_${col}_${i}'"
        fi

        valores+="$valor, "
    done
    # Remove vírgula final
    valores=${valores%, }

    echo "INSERT INTO $tabela ($colunas) VALUES ($valores);"
done

