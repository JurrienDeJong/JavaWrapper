package nl.bioinf.wrapper;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import java.io.IOException;

/**
 * This class reads in data using a given file path, if it
 * does not exist give an error, else return the data
 * @author Jurrien de Jong
 * @version 1.0
 */

public class getData {

    public static Instances loadDataFiles(String datafile) throws IOException {
        try {
            DataSource source = new DataSource(datafile);
            Instances data = source.getDataSet();
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            return data;

        } catch (Exception e) {
            throw new IOException("Reading file gave an error!");
        }
    }
}

