package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import java.util.ArrayList;


public class ClassifyInstances {
    // Only classify a single instance
    protected void classifyNewInstance(J48 model, Instances data, ArrayList<Double> vals) throws  Exception
    {
        double[] values = new double[data.numAttributes()];
        values[0] = vals.get(0);
        values[1] = vals.get(1);
        values[2] = vals.get(2);

        Instance inst = new DenseInstance(1, values);
        inst.setDataset(data);

        double predictedClass = model.classifyInstance(inst);
        System.out.println("Classified Instance:");
        System.out.println("Age ( in years ): " + values[0] +
                "\nSerum Creatinine ( in mg/dL ): " + values[1] +
                "\nSerum Sodium ( in mEq/L ): " + values[2] + "\n");
        printResults(predictedClass);
    }

    protected void classifyInstanceFile(J48 tree, Instances unknownInstances) throws Exception {
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double predictedClass = tree.classifyInstance(unknownInstances.instance(i));
            System.out.println("Classified Instance No: " + (i + 1));

            // Create a shorter version, makes it easier to read.
            // Get the values of each instance attribute:
            Instance inst = unknownInstances.instance(i);
            System.out.println("Age ( in years ): " + Math.round(inst.value(0))+
                    "\nSerum Creatinine ( in mg/dL ): " + inst.value(1) +
                    "\nSerum Sodium ( in mEq/L ): " + inst.value(2) + "\n");
            printResults(predictedClass);
        }
    }

    private void printResults(double predictedClass){
        if (predictedClass == 1.0)
        {
            System.out.println("Result:\nSadly, the patient has a high chance of passing away.");
        } else
        {
            System.out.println("Result:\nThe patient will live!");
        }
        System.out.println("-------------------------------");
    }
}
