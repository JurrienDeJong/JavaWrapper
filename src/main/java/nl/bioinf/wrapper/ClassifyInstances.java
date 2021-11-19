package nl.bioinf.wrapper;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * This class can classify batches of instances, and single ones.
 * The results will also be printed to the user in a descriptive way.
 * @author Jurrien de Jong
 * @version 1.0
 */

public class ClassifyInstances {
    /**
     * Classify a single instance
     * @param model The J48 model
     * @param data known instances
     * @param values double array containing patient data
     */
    // Only classify a single instance
    protected void classifyNewInstance(J48 model, Instances data, double[] values) throws  Exception
    {
        // Create a single instance using DenseInstance:
        Instance inst = new DenseInstance(1, values);

        // set the learning dataset:
        inst.setDataset(data);

        // Get a predicted class ( 0 or 1 ):
        double predictedClass = model.classifyInstance(inst);

        // Return (print) the outcome for the user:
        System.out.println("Classified Instance:");
        System.out.println("\tAge ( in years ): " + values[0] +
                "\n\tSerum Creatinine ( in mg/dL ): " + values[1] +
                "\n\tSerum Sodium ( in mEq/L ): " + values[2] + "\n");
        printResults(predictedClass);
    }

    /**
     * Classify an instance file
     * @param tree The J48 model
     * @param unknownInstances The unknown instances read from file
     */
    protected void classifyInstanceFile(J48 tree, Instances unknownInstances) throws Exception {
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            // Loop over each instance and get the class label:
            double predictedClass = tree.classifyInstance(unknownInstances.instance(i));
            System.out.println("Classified Instance No: " + (i + 1));

            // Create a shorter version, makes it easier to read.
            // Get the values of each instance attribute:
            Instance inst = unknownInstances.instance(i);
            System.out.println("\tAge ( in years ): " + Math.round(inst.value(0))+
                    "\n\tSerum Creatinine ( in mg/dL ): " + inst.value(1) +
                    "\n\tSerum Sodium ( in mEq/L ): " + inst.value(2) + "\n");
            printResults(predictedClass);
        }
    }

    /**
     * Print the result per patient
     * @param predictedClass The class predicted for each entry
     */
    private void printResults(double predictedClass){
        // Print the case for the corresponding Class
        // Switch sadly cannot handle doubles.
        // So another conversion needs to be done:
        switch ((int)predictedClass) {
            case 0:
                System.out.println("Result:\nSadly, the patient has a high chance of passing away.");
                break;
            case 1:
                System.out.println("Result:\nThe patient will live!");
                break;
            default:
                System.out.println("Result:\nThe classification went wrong!");
        }
        System.out.println("\n-------------------------------------\n");
    }
}
