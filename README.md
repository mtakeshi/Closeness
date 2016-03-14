# Closeness

programa escrito em scala para exercício de centralidade de nós.

farness = somatoria das distancias de um ponto para os demais
closeness = 1 / farness

obs: foi considerado que cada aresta (distancia entre pontos vizinhos) vale 1 e não há direção nos relacionamentos.


###para executar o serviço:
- baixar este repositório (git clone https://github.com/mtakeshi/Closeness.git)
- iniciar utilizando o comando: sbt run
	o pacote será compilado e subirá o webservice
	
###para executar o webservice:
é possível chamar os links abaixo:

http://localhost:8080/central
	retorna o nó central da malha (farness, id, closeness)
	
http://localhost:8080/aresta/{id1}-{id2}
	cadastra aresta entre {id1} e {id2}
	obs1: não foi realizado tratamento quando ambos os nós são novos.
	obs2: separador deve ser `-`
	
http://localhost:8080/node/{id}
	retorna os dados do nó solicitado (farness, id, closeness)

http://localhost:8080/nodes
	retorna lista de todos os nós ordenados (farness, id, closeness)

