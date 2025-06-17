
## Exemplo 1: Violação da **1ª Forma Normal (1FN)**

**Regra da 1FN:** Cada coluna deve conter apenas valores atômicos (indivisíveis), sem listas ou conjuntos.

### Tabela violando 1FN:

| id\_pedido | cliente | produtos      |
| ---------- | ------- | ------------- |
| 1          | João    | Camisa, Calça |
| 2          | Maria   | Sapato        |

* A coluna `produtos` armazena vários valores juntos (lista de produtos), o que **quebra a atomicidade**.

---

## Exemplo 2: Violação da **2ª Forma Normal (2FN)**

**Regra da 2FN:** Todos os atributos não-chave devem depender da chave inteira (sem dependência parcial).

### Tabela violando 2FN (chave composta: `id_pedido`, `id_produto`):

| id\_pedido | id\_produto | nome\_produto | quantidade | nome\_cliente |
| ---------- | ----------- | ------------- | ---------- | ------------- |
| 1          | 10          | Camisa        | 2          | João          |
| 1          | 11          | Calça         | 1          | João          |

* `nome_cliente` depende só de `id_pedido` (parte da chave), não da chave toda (que inclui `id_produto`).
* Isso é **dependência parcial** — viola a 2FN.

---

## Exemplo 3: Violação da **3ª Forma Normal (3FN)**

**Regra da 3FN:** Não deve haver dependências transitivas (atributos não-chave não dependem de outros atributos não-chave).

### Tabela violando 3FN:

| id\_cliente | nome\_cliente | cidade         | estado | regiao  |
| ----------- | ------------- | -------------- | ------ | ------- |
| 1           | João          | São Paulo      | SP     | Sudeste |
| 2           | Maria         | Rio de Janeiro | RJ     | Sudeste |

* `regiao` depende de `estado` (atributo não-chave) e não diretamente da chave (`id_cliente`).
* Isso é dependência transitiva — quebra a 3FN.


