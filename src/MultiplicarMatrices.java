
public class MultiplicarMatrices {

    int[][] mat1;
    int[][] mat2;
    int[][] mat3;



    public void multiplicar_matrices(int nf1, int nc1, int nc2){
        for (int i = 0; i<nf1; i++){ //numero de filas mat1 - 1
            for (int j=0; j<nc2; j++){ //numero de columnas mat2 - 1
                int acum = 0;
                for (int k=0; k<nc1; k++){ //numero de columnas mat1 y filas mat2 (- 1)
                    acum += mat1[i][k] * mat2[k][j]; //producto punto entre fila i mat1 y columna j mat2 
                                                    // Despues pasa a la siguiente columna 
                }
                mat3[i][j] = acum; //fila i columna j (mat3 = nf1 x nc2)
            }
        }
    }

    public int elm_to_dv (int i, int j, int nf1, int nc1, int nc2, short matriz_n){
        int dv_elm = (i * nc1 + j)*4 ;
        int dv_matriz = 0;

        if (matriz_n == 2){
            dv_matriz =  
        }

        return retorno;
    }

    public void paginacion_matrices(int nf1, int nc1, int nc2, int tp, String file_name){

    }


    public static void main(String[] args) {
        System.out.println("a");
    }
    
}
