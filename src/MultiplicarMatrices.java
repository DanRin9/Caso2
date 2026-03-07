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

    public int dv_to_page (int dv, int tp){
        return dv / tp;
    }

    public int dv_to_offset(int dv, int tp){
        return dv % tp;
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

        int dv_elm_ik = 0; //Mat1[i][k]
        int dv_elm_kj = 0;//Mat2[k][j]
        int dv_elm_ij = 0;//Mat3[i][j]
        //# Pagina, offset (posicion)
        String[] pgs_offset_ik = new String[2];
        String[] pgs_offset_kj = new String[2];
        String[] pgs_offset_ij = new String[2];

        //Bucle
        for (int i = 0; i<nf1; i++){ //numero de filas mat1 - 1
            for (int j=0; j<nc2; j++){ //numero de columnas mat2 - 1
                for (int k=0; k<nc1; k++){ //numero de columnas mat1 y filas mat2 (- 1)

                    //dvs de ik y kj
                    dv_elm_ik = elm_to_dv(i, k, nf1, nc1, nc2, (short)1);
                    dv_elm_kj = elm_to_dv(k, j, nf1, nc1, nc2, (short)2);

                }
                
            }
        }

        return matriz_pagina_offset;

    }


    public static void main(String[] args) {
        System.out.println("a");
    }
    
}
