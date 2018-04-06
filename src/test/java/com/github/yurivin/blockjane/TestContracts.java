package com.github.yurivin.blockjane;

import com.github.yurivin.blockjane.contracts.ContractProcessor;
import com.github.yurivin.blockjane.contracts.iContractProcessor;
import com.github.yurivin.blockjane.infrastracture.Environment;
import org.junit.Assert;
import org.junit.Test;

public class TestContracts {

    final String groovyClassStr = "import com.github.yurivin.blockjane.contracts.iContract\n" +
            "\n" +
            "class TestContract implements iContract {\n" +
            "    @Override\n" +
            "    byte[] execute() {\n" +
            "        return new String(\"Hello!\").getBytes(\"UTF-8\")\n" +
            "    }\n" +
            "}";

    @Test
    public void test() throws Exception {

        Environment env = new Environment();
        iContractProcessor processor = new ContractProcessor(env);

        byte[] result = processor.loadAndRun(groovyClassStr);
        Assert.assertEquals("Hello!", new String(result, "UTF-8"));
    }
}
