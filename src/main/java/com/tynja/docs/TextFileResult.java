/*
* Copyright 2012 Tommy Tynjä
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.tynja.docs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Prototype result implementation that produces a simple
 * text file in the target/docs directory.
 *
 * @author Tommy Tynj&auml;
 */
public class TextFileResult extends Result {

    protected static final String outputDirectory = "target/docs/";

    @Override
    public void produce() {
        File file = getOutputFile();
        try (FileWriter out = new FileWriter(file, true)) {
            String result = getTitle() + System.lineSeparator();
            result += titleRow("input");
            for (String name : getParameters().keySet())
                result += "\t\t" + name + ": " + getParameters().get(name) + System.lineSeparator();
            result += titleRow("produces");
            for (String name : getResults().keySet())
                result += "\t\t" + name + ": " + getResults().get(name) + System.lineSeparator();
            out.write(result + System.lineSeparator());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private File getOutputFile() {
        createOutputDirectory();
        String outputFile = outputDirectory + getCategory() + ".txt";
        try {
            File file = new File(outputFile);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Could not create " + outputFile);
                }
            }
            return file;
        } catch (IOException e) {
            throw new IllegalStateException("Could not create documentation output file: " + outputFile, e);
        }
    }

    private String titleRow(final String title) {
        return System.lineSeparator() + "\t" + title + " ->" + System.lineSeparator();
    }

    private void createOutputDirectory() {
        File outputDir = new File(outputDirectory);
        if (outputDir.exists() && !outputDir.isDirectory()) {
            outputDir.delete();
        }
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
    }
}
