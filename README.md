# Closeness

programa escrito em scala para exercício de centralidade de nós (fontes relacionados ao webservice foram adaptados de fontes existentes).

	**farness** = somatoria das distancias de um ponto para os demais

	**closeness** = 1 / farness

obs: foi considerado que cada aresta (distancia entre pontos vizinhos) vale 1 e não há direção nos relacionamentos.


###lógica:

	passo 1: lista todas as arestas existentes

	passo 2: lista todos os vértices existentes

	passo 3: elege vértice inicial (23) na lista de vértices

		retira vértice inicial visitado da lista

	passo 4: verifica todas as arestas (lista) onde o vértice aparece (23 30), (23, 35), (41 23), (56 23), ... 

		retira as arestas que contenham vértices visitados
	
		lista vértices-destino das arestas listadas

	passo 5: volta ao passo 3 fornecendo lista vértices-destino (agora funcionando como lista vértices-origem) até que todos os vértices tenham sido visitados

		a cada iteração é somado um nível e multiplicado pela quantidade de vértices encontrados (ao final temos a distância do vértice inicial para os demais vértices)

	o cálculo é feito para cada vértice encontrado no passo 2


###para subir o serviço:
	- baixar este repositório (git clone https://github.com/mtakeshi/Closeness.git)
	- iniciar utilizando o comando: **sbt run**

		as dependências serão baixadas, o pacote será compilado e webservice será colocado no ar
	
###para chamar o webservice:
é possível chamar os links abaixo:

	**http://localhost:8080/central**

		retorna o nó central da malha (farness, id, closeness)
	
	**http://localhost:8080/aresta/{id1}-{id2}**

		cadastra aresta entre {id1} e {id2}

		obs1: não foi realizado tratamento quando ambos os nós são novos.

		obs2: separador deve ser `-`
	
	**http://localhost:8080/node/{id}**

		retorna os dados do nó solicitado (farness, id, closeness)

	**http://localhost:8080/nodes**

		retorna lista de todos os nós ordenados (farness, id, closeness)

