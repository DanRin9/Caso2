
public class MultiplicarMatrices {

    int[][] mat1;
    int[][] mat2;
    int[][] mat3;



    public void multiplicar_matrices(int nf1, int nc1, int nc2){
        for (int i = 0; i<nf1; i++){
            for (int j=0; j<nc2; j++){
                int acum = 0;
                for (int k=0; k<nc1; k++){
                    acum += mat1[i][k] * mat2[k][j];
                }
                mat3[i][j] = acum;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("a");
    }
    
}
