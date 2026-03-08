import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public int elm_to_dv (int i, int j, int nf1, int nc1, int nc2, int matriz_n){
        int dv_elm = 0;
        int dv_matriz = 0;
        int dv = 0;

        if (matriz_n == 2){
            dv_elm = (i * nc2 + j)*4;
            dv_matriz = nf1 * nc1 * 4;
        }else if(matriz_n == 3){
            dv_elm = (i * nc2 + j)*4;
            dv_matriz = 4*nc1*(nf1+nc2);
        }else{
            dv_elm = (i * nc1 + j)*4;
        }
        
        dv = dv_matriz + dv_elm;

        System.out.println("DEBUG elm_to_dv: i=" + i + ", j=" + j + ", matriz_n=" + matriz_n
                + ", dv_elm=" + dv_elm + ", dv_matriz=" + dv_matriz + ", dv=" + dv);

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
            retorno = retorno.replace("x", "2");
            retorno = retorno.replace("f", k2);
            retorno = retorno.replace("c", j2);

        }else if (n_matriz == 3){
            retorno = retorno.replace("x", "3");
            retorno = retorno.replace("f", i2);
            retorno = retorno.replace("c", j2);
        }else{
            retorno = retorno.replace("x", "1");
            retorno = retorno.replace("f", i2);
            retorno = retorno.replace("c", k2);

        }

        return retorno;
    }

    public void writeFile(int nf1, int nc1, int nc2, int tp, String file_name, ArrayList<String> paginacion){
        String nf1text = Integer.toString(nf1);
        String nc1text = Integer.toString(nc1);
        String nc2text = Integer.toString(nc2);
        String tptext = Integer.toString(tp);

        //Calculo de cantidad de dvs
        int length = paginacion.size();
        String text_length = Integer.toString(length);


        //Calculo de paginas
        // 1 direccion -> 4 bytes
        // # pgs = #direcciones * 4 / TP
        double np = Math.ceil(length / tp);
        String np_text = Double.toString(np);

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file_name));
            writer.write("TP = "+tptext);
            writer.newLine();
            writer.write("NF1 = "+nf1text);
            writer.newLine();
            writer.write("NC1 = "+ nc1text);
            writer.newLine();
            writer.write("NF2 = "+ nc1text);
            writer.newLine();
            writer.write("NC2 = "+nc2text);
            writer.newLine();
            writer.write("NR = "+text_length);
            writer.newLine();
            writer.write("NP = "+np_text);
            writer.newLine();
            for (String linea : paginacion){
                writer.write(linea);
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paginacion_matrices(int nf1, int nc1, int nc2, int tp, String file_name){
        //Var Locs
        //Array de Strings tipo ["Mx-f-c","pg","offset"]
        ArrayList<String> matriz_pagina_offset = new ArrayList<>();

        int dv_elm_ik; //Mat1[i][k]

        int dv_elm_kj;//Mat2[k][j]
        
        int dv_elm_ij;//Mat3[i][j]


        //Bucle
        for (int i = 0; i<nf1; i++){ //numero de filas mat1 - 1
            for (int j=0; j<nc2; j++){ //numero de columnas mat2 - 1
               
                for (int k=0; k<nc1; k++){ //numero de columnas mat1 y filas mat2 (- 1)

                    //formato, pagina y offset Matriz 1
                    dv_elm_ik = elm_to_dv(i, k, nf1, nc1, nc2, 1);
                    String matriz1 = matrizx_ijk_to_string(i, j, k, 1);
                    String pg = dv_to_page(dv_elm_ik, tp);
                    String offset = dv_to_offset(dv_elm_ik, tp);
                    System.out.println("DEBUG paginacion_matrices: i=" + i + ", j=" + j + ", k=" + k
                            + ", dv=" + dv_elm_ik + ", pg=" + pg + ", offset=" + offset);
                    matriz_pagina_offset.add(String.join(", ", matriz1, pg, offset));

                    //formato, pagina y offset Matriz 2
                    dv_elm_kj = elm_to_dv(k, j, nf1, nc1, nc2, 2);
                    String matriz2 = matrizx_ijk_to_string(i, j, k, 2);
                    String pg2 = dv_to_page(dv_elm_kj, tp);
                    String offset2 = dv_to_offset(dv_elm_kj, tp);
                    System.out.println("DEBUG paginacion_matrices: i=" + i + ", j=" + j + ", k=" + k
                            + ", dv=" + dv_elm_kj + ", pg=" + pg2 + ", offset=" + offset2);
                    matriz_pagina_offset.add(String.join(", ",matriz2, pg2, offset2));

                }

                //formato, pagina y offset Matriz 2
                dv_elm_ij = elm_to_dv(i, j, nf1, nc1, nc2, 3);
                String matriz3 = matrizx_ijk_to_string(i, j, 0, 3);
                String pg3 = dv_to_page(dv_elm_ij, tp);
                String offset3 = dv_to_offset(dv_elm_ij, tp);
                System.out.println("DEBUG paginacion_matrices: i=" + i + ", j=" + j + ", k=0"
                        + ", dv=" + dv_elm_ij + ", pg=" + pg3 + ", offset=" + offset3);
                matriz_pagina_offset.add(String.join(", ",matriz3, pg3, offset3));
                
            }
        }

        writeFile(nf1, nc1, nc2, tp, file_name, matriz_pagina_offset);

    }


    public static void main(String[] args) {
        MultiplicarMatrices mm = new MultiplicarMatrices();
        mm.paginacion_matrices(4, 6, 8, 64, "aura.txt");
    }
    
}
