/*
 * Módulo 4
 * Projeto e Construção de Sistemas
 * Professor: Alexandre Correa
 * 
 * Implementado por Zeno Rocha
 * Em 03/04/2011
 * 
 * --------------------------------------------------------------------------------------------
 * 
 * Programa Recursos Rodoviários
 * 
 * 	Este é um exercício de reestruturação de um fragmento de uma aplicação, que embora
 * 	tenha sido feita em Java, está longe de ser aderente ao paradigma de orientação a objetos.
 * 	Note por exemplo que os atributos foram definidos com o modificador public e a
 * 	implementação horrível da operação getOcupacao da classe LinhaFerroviaria, com várias
 * 	expressões de downcasting. Sua tarefa é reestruturar a implementação destas classes de
 * 	forma a torná-la orientada a objetos, a partir da utilização efetiva de recursos como
 * 	information hiding e polimorfismo.
 * 
 * 	Apenas para explicar o significado das classes, uma Linha Ferroviária em uma estação de
 * 	manobra pode ter diversos recursos nela estacionados. Os recursos podem ser locomotivas
 * 	ou vagões isolados ou um trem (formado por locomotivas e vagões). O espaço ocupado por
 * 	cada recurso depende do tipo do recurso. No caso de uma locomotiva, o espaço é dado pelo
 * 	seu comprimento. No caso de um vagão, o espaço é dado pela soma do comprimento dos
 * 	engates mais o comprimento entre testeiras (este último corresponde ao local onde a carga
 * 	efetivamente é acondicionada). Finalmente, no caso de um trem, o comprimento é dado
 * 	pela soma do comprimento dos recursos que o compõem (vagões e locomotivas, calculados
 * 	conforme descrito acima).
 * 
 * 	Neste exercício, você nem precisa se preocupar muito com o significado da aplicação. 
 * 	A idéia é que você aprenda a reconhecer padrões de estruturação ruim de uma aplicação
 * 	orientada a objetos (conhecidos também como anti-padrões) e sugerir uma solução mais adequada.
 * 
 */

package com.zenorocha.modulo4;

public class LinhaFerroviaria {
    private int numero;
    private Recurso[] recursosEstacionados;

    public LinhaFerroviaria(Recurso[] recursos) {
        this.recursosEstacionados = recursos;
    }

    public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Recurso[] getRecursosEstacionados() {
		return recursosEstacionados;
	}

	public void setRecursosEstacionados(Recurso[] recursosEstacionados) {
		this.recursosEstacionados = recursosEstacionados;
	}

	public int getOcupacao() {
        int ocupacao = 0;
        
        for (int i = 0; i < recursosEstacionados.length; i++) {
            if (this.recursosEstacionados[i] instanceof Vagao)
                ocupacao = ocupacao + ((Vagao)this.recursosEstacionados[i]).getComprimentoEngates() + ((Vagao)this.recursosEstacionados[i]).getComprimentoTesteiras();
            else if (this.recursosEstacionados[i] instanceof Locomotiva)
                ocupacao = ocupacao + ((Locomotiva)this.recursosEstacionados[i]).getComprimento();
            else {
                Trem trem = ((Trem)this.recursosEstacionados[i]);
                for (int j = 0; j < trem.getRecursosDoTrem().length; j++) {
                    if (trem.getRecursosDoTrem(j) instanceof Vagao)
                        ocupacao = ocupacao + ((Vagao)trem.getRecursosDoTrem(j)).getComprimentoEngates() + ((Vagao) trem.getRecursosDoTrem(j)).getComprimentoTesteiras();
                    else if (trem.getRecursosDoTrem(j) instanceof Locomotiva)
                        ocupacao = ocupacao + ((Locomotiva)trem.getRecursosDoTrem(j)).getComprimento();
                }
            }
        }

        return ocupacao;
    }
}
