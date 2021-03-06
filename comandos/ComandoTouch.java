package TrabalhoED1_ArvoreBinaria.comandos;

import TrabalhoED1_ArvoreBinaria.elementos.Arquivo;
import TrabalhoED1_ArvoreBinaria.elementos.ArvoreBinariaBusca;
import TrabalhoED1_ArvoreBinaria.elementos.Diretorio;
import TrabalhoED1_ArvoreBinaria.exceptions.ArquivoExistenteException;
import TrabalhoED1_ArvoreBinaria.exceptions.FaltaOperandoException;
import TrabalhoED1_ArvoreBinaria.exceptions.NaoEDiretorioException;
import TrabalhoED1_ArvoreBinaria.exceptions.DiretorioInexistenteException;
import TrabalhoED1_ArvoreBinaria.funcoes.Funcao;


public class ComandoTouch implements Funcao{

    @Override
    public void fazFuncao(ArvoreBinariaBusca arvore, String... resComando) throws Exception {
        int index;
        
        if(resComando.length == 1){ //Não foi adicionado qual chave deseja adicionar
            throw new FaltaOperandoException(resComando[0]);
        }else if(resComando.length == 2){
            if(resComando[1].contains("/")){ //Com path
                index = resComando[1].lastIndexOf('/'); //Separa a chave a ser adicionada do path
                Arquivo dir = arvore.interpretaPath(resComando[1].substring(0, index));
                if(dir == null){
                    throw new DiretorioInexistenteException(resComando[0], resComando[1]);
                }else if(!(dir instanceof Diretorio)){
                    throw new NaoEDiretorioException(resComando[0], resComando[1]);
                }else{
                    if(((Diretorio) dir).getDir().procuraArquivo(resComando[1].substring(index+1)) != null)
                        throw new ArquivoExistenteException(resComando[1]);
                    ((Diretorio) dir).getDir().addArquivo(resComando[1].substring(index+1));
                }
            }else{ //Sem path
                if(arvore.procuraArquivo(resComando[1]) != null)
                    throw new ArquivoExistenteException(resComando[1]);
                arvore.addArquivo(resComando[1]);
            }
        }
    }
    
}
