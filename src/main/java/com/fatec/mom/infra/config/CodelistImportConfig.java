package com.fatec.mom.infra.config;

import com.fatec.mom.infra.codelist.reader.CodelistReader;
import com.fatec.mom.infra.codelist.reader.CodelistReaderType;
import com.fatec.mom.infra.codelist.reader.DefaultCodelistReader;
import com.fatec.mom.infra.codelist.reader.cellreaders.HeadersReader;
import com.fatec.mom.infra.codelist.reader.cellreaders.ValuedRowsReader;
import com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions.DefaultHeaderReadingCondition;
import com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions.DefaultRowReadingCondition;
import com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers.CellValueRetriever;
import com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers.CellValueRetrieverLocator;
import com.fatec.mom.infra.codelist.reader.codelistBuilder.CodelistBuiderInvoker;
import com.fatec.mom.infra.codelist.reader.codelistBuilder.CodelistBuilder;
import com.fatec.mom.infra.codelist.reader.codelistBuilder.CodelistMetadata;
import com.fatec.mom.infra.codelist.reader.config.*;
import com.fatec.mom.infra.codelist.reader.contentsretrievers.MultipleTabsContentRetriever;
import com.fatec.mom.infra.codelist.reader.contentsretrievers.SingleTabContentRetriever;
import com.fatec.mom.infra.codelist.reader.domain.CellValueType;
import com.fatec.mom.infra.codelist.reader.metadataretrievers.*;
import com.fatec.mom.infra.codelist.reader.sheetreader.DefaultSheetReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class CodelistImportConfig {

    @Value("${default-documents-path}")
    private String DOCUMENTS_PATH;

    @Autowired
    private CodelistConfigLocator configLocator;

    @Bean
    public Map<CodelistConfigType, CodelistConfig> codelistConfiguration() {
        final Map<CodelistConfigType, CodelistConfig> config = new EnumMap<>(CodelistConfigType.class);
        config.put(CodelistConfigType.DEFAULT, getDefaultConfig());
        return config;
    }

    @Bean
    public Map<CodelistReaderType, CodelistReader> readers(CellValueRetrieverLocator locator) {
        final Map<CodelistReaderType, CodelistReader> readers = new EnumMap<>(CodelistReaderType.class);
        readers.put(CodelistReaderType.SINGLE_TAB, getDefaultCodelistReader(locator));
        readers.put(CodelistReaderType.MULTIPLE_TAB, getMultipleTabsCodelistReader(locator));
        return readers;
    }

    public CodelistReader getMultipleTabsCodelistReader(CellValueRetrieverLocator retrieverLocator) {
        return new DefaultCodelistReader(
                new MultipleTabsContentRetriever(
                        new DefaultSheetReader(
                                retrieverLocator,
                                new HeadersReader(retrieverLocator, new DefaultHeaderReadingCondition()),
                                new ValuedRowsReader(retrieverLocator, new DefaultRowReadingCondition()))),
                configLocator);
    }

    public CodelistReader getDefaultCodelistReader(CellValueRetrieverLocator retrieverLocator) {
        return new DefaultCodelistReader(
                new SingleTabContentRetriever(
                    new DefaultSheetReader(
                            retrieverLocator,
                            new HeadersReader(retrieverLocator, new DefaultHeaderReadingCondition()),
                            new ValuedRowsReader(retrieverLocator, new DefaultRowReadingCondition()))),
                configLocator);
    }

    public CodelistConfig getDefaultConfig() {
        final Map<CodelistMetadata, Object> metadataIndexes = new EnumMap<>(CodelistMetadata.class);
        metadataIndexes.put(CodelistMetadata.SECTION_COLUMN, 0);
        metadataIndexes.put(CodelistMetadata.SUB_SECTION_COLUMN, 1);
        metadataIndexes.put(CodelistMetadata.NUMBER_COLUMN, 2);
        metadataIndexes.put(CodelistMetadata.NAME_COLUMN, 3);
        metadataIndexes.put(CodelistMetadata.CODE_COLUMN, 4);
        metadataIndexes.put(CodelistMetadata.REMARKS_COLUMN, 5);
        metadataIndexes.put(CodelistMetadata.TRAIT_VALUES_BEGIN, 6);

        final CodelistSheetConfig sheetConfig = new CodelistSheetConfig(
                new HeaderCellData(0, Collections.singletonList(0)),
                new SingleCellData(0, 1),
                Arrays.asList(
                        new SheetNameRetriever(),
                        new DocumentNameOnSheetNameRetriever(),
                        new PartNumberOnSheetNameRetriever(),
                        new DocumentTraitOnCellRetriever(new SingleCellData(6, 0))
                ));

        final CodelistBuiderInvoker invoker = new CodelistBuiderInvoker(new CodelistBuilder(DOCUMENTS_PATH, metadataIndexes));

        return new SingleConfig(invoker, new CodelistSheetConfigPerTab(0, sheetConfig));
    }

    @Bean
    public List<MetadataRetriever> metadataRetrievers() {
        return Collections.singletonList(new SheetNameRetriever());
    }

    @Bean
    public Map<CellValueType, CellValueRetriever> cellValueRetrieverMap(
            @Qualifier("stringCellValueRetriever") final CellValueRetriever stringRetriever,
            @Qualifier("numericCellValueRetriever") final CellValueRetriever numericRetriever,
            @Qualifier("formulaCellValueRetriever") final CellValueRetriever formulaRetriever,
            @Qualifier("emptyCellValueRetriever") final CellValueRetriever emptyRetriever,
            @Qualifier("blankCellValueRetriever") final CellValueRetriever blankRetriever) {
        final Map<CellValueType, CellValueRetriever> retrieverMap = new EnumMap<>(CellValueType.class);
        retrieverMap.put(CellValueType.STRING, stringRetriever);
        retrieverMap.put(CellValueType.NUMERIC, numericRetriever);
        retrieverMap.put(CellValueType.FORMULA, formulaRetriever);
        retrieverMap.put(CellValueType.EMPTY, emptyRetriever);
        retrieverMap.put(CellValueType.BLANK, blankRetriever);
        return retrieverMap;
    }

}
