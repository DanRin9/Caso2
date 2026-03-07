import java.util.*;

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
        int dv = 0;

        if (matriz_n == 2){
            dv_matriz = nf1 * nc1 * 4;
        }else if(matriz_n == 3){
            dv_matriz = 4*nc1*(nf1+nc2);
        }
        
        dv = dv_matriz + dv_elm;

        return dv;
    }

    public String dv_to_page (int dv, int tp){
        int pg = dv / tp;
        return Integer.toString(pg);
    }

    public String dv_to_offset(int dv, int tp){
        int module = dv % tp;
        return Integer.toString(module);
    }

    public String matrizx_ijk_to_string (int i, int j, int k, int n_matriz){
        String i2 = Integer.toString(i);
        String j2 = Integer.toString(j);
        String k2 = Integer.toString(k);

        //M1 o M2 o M3
        String retorno = "[Mx-f-c]";
        if (n_matriz == 2){
            retorno.replace("x", "2");
            retorno.replace("f", k2);
            retorno.replace("c", j2);

        }else if (n_matriz == 3){
            retorno.replace("x", "3");
            retorno.replace("f", i2);
            retorno.replace("c", j2);
        }else{
            retorno.replace("x", "1");
            retorno.replace("f", i2);
            retorno.replace("c", k2);

        }

        return retorno;
    }

    public ArrayList<String[]> paginacion_matrices(int nf1, int nc1, int nc2, int tp, String file_name){
        //Var Locs
        //Array de Strings tipo ["Mx-f-c","pg","offset"]
        ArrayList<String[]> matriz_pagina_offset = new ArrayList<>();

        int dv_elm_ik; //Mat1[i][k]
        String matriz1_format;

        int dv_elm_kj;//Mat2[k][j]
        String matriz2_format;
        
        int dv_elm_ij;//Mat3[i][j]
        String matriz3_format;
    

       

        //Bucle
        for (int i = 0; i<nf1; i++){ //numero de filas mat1 - 1
            for (int j=0; j<nc2; j++){ //numero de columnas mat2 - 1
                //Matriz ijk, # Pagina, offset (posicion)
                String[] mat3 = new String[3];

                for (int k=0; k<nc1; k++){ //numero de columnas mat1 y filas mat2 (- 1)

                    String[] mat1 = new String[3];
                    String[] mat2 = new String[3];
                    
                    //formato, pagina y offset Matriz 1
                    dv_elm_ik = elm_to_dv(i, k, nf1, nc1, nc2, (short)1);
                    mat1[0] = matrizx_ijk_to_string(i, j, k, 1);
                    mat1[1] = dv_to_page(dv_elm_ik, tp);
                    mat1[2] = dv_to_offset(dv_elm_ik, tp);


                    //formato, pagina y offset Matriz 2
                    dv_elm_kj = elm_to_dv(k, j, nf1, nc1, nc2, (short)2);
                    mat2[0] = matrizx_ijk_to_string(i, j, k, 2);
                    mat2[1] = dv_to_page(dv_elm_ik, tp);
                    mat2[2] = dv_to_offset(dv_elm_ik, tp);
                    
                    



                }
                
            }
        }

        return matriz_pagina_offset;

    }


    public static void main(String[] args) {
        System.out.println("a");
    }
    
}
