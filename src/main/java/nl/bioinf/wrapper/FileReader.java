package nl.bioinf.wrapper;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.IOException;

public class FileReader {

    public Instances loadArff(String datafile) throws IOException {
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(datafile);
            Instances data = source.getDataSet();
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            throw new IOException("could not read from file");
        }
    }
}
