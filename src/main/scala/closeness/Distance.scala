package closeness

import scala.collection.mutable.ListBuffer

//premissas: arestas unitarias e sem direcao
class Distance {
  
  var arestas = carregaEdges("edges")  //chamada da carga de arestas (945 registros)
  var vertices = new ListBuffer[Vertice]
  var calculado = false
  
  //metodo para carga do arquivo com arestas
  def carregaEdges(nmArq: String): List[List[String]] = {
    val list = new ListBuffer[List[String]]()
  
    val url = getClass.getResource("/edges")
    val edges = scala.io.Source.fromURL(url)  //abre arquivo
    var str = edges.getLines().toList  //captura linhas e converte em lista
    
    str.foreach { linha =>
      var vs = linha.split(" ").toList  //split das linhas pelo espaco, converte em lista
      list += vs  //adiciona a lista de retorno
    }
    
    edges.close()  //fecha arquivo
    list.toList  //retorna lista
  }
  
  //calcula as distancias entre vertice inicial e demais vertices
  def calculaDistancia(vx: List[String], ed: List[List[String]], vo: ListBuffer[List[String]], nivel: Int): Int  = { 
    //retirar vertices visitados
    var vx2 = vx diff vo.toList.flatten
  
    var listV = new ListBuffer[List[String]]()
    var listE = new ListBuffer[List[String]]()
    ed.foreach { item =>    //varre as arestas
      if ((item diff vo.toList.flatten).length < 2) {  //procurando registros com lista de vertices origem
        var lst = item diff vo.toList.flatten
        if (lst.length > 0) listV += lst
        listE += item
      }
      listV = listV.distinct  //limpa itens duplicados
    }
    var ed2 = (ed diff listE)
    
    if (vx2.length == 0) listV.length * nivel
    else listV.length * nivel + calculaDistancia(vx2, ed2, listV, nivel + 1)
  }

  //iteracao de calculo de distancia para cada vertice
  def verificaFarness() = {
    if (!calculado) {  //realiza os calculos uma vez, tornando a realizar apenas quando inserida nova aresta
      vertices = new ListBuffer[Vertice]
      var vertex = arestas.flatten.distinct  //isola os vertices para verificar a proximidade (closeness) dos pares
    
      vertex.foreach { x =>  //adota cada vertice como ponto inicial e calcula distancias
        val v0 = new ListBuffer[List[String]]()
        v0 += List(x)
        var distancia = calculaDistancia(vertex, arestas, v0, 1)
        vertices += new Vertice(distancia, x, math.pow(distancia, -1))
      }
      calculado = true
    }
  }
  
  //adiciona aresta na lista
  def adicionaAresta(vx: String) = {
    var vs = vx.split("-").toList  //split das linhas pelo separador, converte em lista
    arestas ::= vs  //adiciona a lista de retorno
    calculado = false
    vs
  }
  
  //retorna primeiro elemento dos vertices (quando ordenado pela menor distancia equivale ao ponto central)
  def central = {
    verificaFarness()
    val sortedVertices = vertices.sortWith(_.farness < _.farness)
    sortedVertices.head
  }
  
  def getNode(id: String): List[Any] = {
    verificaFarness()
    var v = vertices find { x => x.nome == id }
    List(v)
  }
  
  def getNodes(): ListBuffer[Vertice] = {
    verificaFarness()
    val sortedVertices = vertices.sortWith(_.farness < _.farness)
    sortedVertices
  }
  
}