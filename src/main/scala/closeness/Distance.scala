package closeness

import scala.collection.mutable.ListBuffer

//premissas: arestas unitarias e sem direcao
class Distance {
  
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
    
//    println(nivel + "(" + vo.length + ") >> " + nivel * listV.length)
    
    if (vx2.length == 0) listV.length * nivel
    else listV.length * nivel + calculaDistancia(vx2, ed2, listV, nivel + 1)
  }
  
  //********************************
  
  def verificaFarness() = {
    var edges = carregaEdges("edges")  //chamada da carga de arestas (945 registros)
    
    var vertex = edges.flatten.distinct  //isola os vertices para verificar a proximidade (closeness) dos pares
  
    
    vertex.foreach { x =>
      val v0 = new ListBuffer[List[String]]()
      v0 += List(x)
      var distancia = calculaDistancia(vertex, edges, v0, 1)
      println(distancia + " [" + x + "] " + scala.math.pow(distancia, -1))    
    }
  }
  
}