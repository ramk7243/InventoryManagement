package com.demo.inventory.management.utils;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.demo.inventory.management.exception.InventoryException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

@Component
public class SchemaValidationUtils
{
    public static final String JSON_V4_SCHEMA_IDENTIFIER = "http://json-schema.org/draft-04/schema#";

    public static final String JSON_SCHEMA_IDENTIFIER_ELEMENT = "$schema";

    public static JsonNode getJsonNode(String jsonText) throws IOException
    {
        return JsonLoader.fromString(jsonText);
    } // getJsonNode(text) ends

    public static JsonSchema getSchemaNode(String schemaText) throws IOException, ProcessingException
    {
        final JsonNode schemaNode = getJsonNode(schemaText);
        return _getSchemaNode(schemaNode);
    } // getSchemaNode(text) ends

    public static void validateJson(JsonSchema jsonSchemaNode, JsonNode jsonNode) throws ProcessingException
    {
        ProcessingReport report = jsonSchemaNode.validate(jsonNode);
        if (!report.isSuccess()) {
            for (ProcessingMessage processingMessage : report) {
                throw new ProcessingException(processingMessage);
            }
        }
    } // validateJson(Node) ends

    public static boolean isJsonValid(JsonSchema jsonSchemaNode, JsonNode jsonNode) throws ProcessingException
    {
        ProcessingReport report = jsonSchemaNode.validate(jsonNode);
        if (!report.isSuccess()) {
            java.lang.String errorMessage = "";
            for (ProcessingMessage i : report) {
                errorMessage = errorMessage + i.getMessage();
                errorMessage = errorMessage + " : ";
            }
            throw new InventoryException(errorMessage, HttpStatus.BAD_REQUEST, ErrorType.BAD_REQUEST);
        }
        return report.isSuccess();
    } // validateJson(Node) ends

    public static boolean isJsonValid(String schemaText, String jsonText)
    {
        boolean isValid = false;
        try {
            final JsonSchema schemaNode = getSchemaNode(schemaText);
            final JsonNode jsonNode = getJsonNode(jsonText);
            isValid = isJsonValid(schemaNode, jsonNode);
        } catch (ProcessingException e) {
            throw new InventoryException(e.getMessage(),HttpStatus.BAD_REQUEST,ErrorType.BAD_REQUEST);
        } catch (IOException e) {
            throw new InventoryException(e.getMessage(),HttpStatus.BAD_REQUEST,ErrorType.BAD_REQUEST);
        }
        return isValid;
    } // validateJson(Node) ends

    public static void validateJson(String schemaText, String jsonText) throws IOException, ProcessingException
    {
        final JsonSchema schemaNode = getSchemaNode(schemaText);
        final JsonNode jsonNode = getJsonNode(jsonText);
        validateJson(schemaNode, jsonNode);
    } // validateJson(text) ends

    private static JsonSchema _getSchemaNode(JsonNode jsonNode) throws ProcessingException
    {
        final JsonNode schemaIdentifier = jsonNode.get(JSON_SCHEMA_IDENTIFIER_ELEMENT);
        if (null == schemaIdentifier) {
            ((ObjectNode) jsonNode).put(JSON_SCHEMA_IDENTIFIER_ELEMENT, JSON_V4_SCHEMA_IDENTIFIER);
        }

        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        return factory.getJsonSchema(jsonNode);
    } // _getSchemaNode() ends
}

