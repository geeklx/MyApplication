package com.example.p010_recycleviewall.net.parser;

import org.loader.glin.factory.ParserFactory;
import org.loader.glin.parser.Parser;

/**
 * Created by qibin on 2016/7/13.
 */

public class FastJsonParserFactory implements ParserFactory {

    @Override
    public Parser getParser() {
        return new CommParser("data");
    }

    @Override
    public Parser getListParser() {
        return new ListParser("data");
    }
}