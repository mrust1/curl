@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.1')

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import groovy.cli.*

def cli = new CliBuilder()
cli.with {
    usage: 'groovy CurlExample.groovy [options] <url>'
    option('m', 'method', 'HTTP method', true)
    option('H', 'header', 'HTTP headers', true, true)
    option('d', 'data', 'HTTP request body')
}

def options = cli.parse(args)
def url = options.arguments() ? options.arguments()[0] : ''
def method = options.method ?: 'GET'
def headers = options.header ?: []
def body = options.data ?: ''

def sendHttpRequest(String url, String method, Map headers = [:], String body = '') {
    def http = new HTTPBuilder(url)
    
    http.request(Method.valueOf(method.toUpperCase())) { req ->
        headers.each { k, v -> req.headers[k] = v }
        
        if (body) {
            if (headers['Content-Type']?.toLowerCase() == 'application/json') {
                req.body = body as JSON
            } else {
                req.body = body
            }
        }
        
        response.success = { resp, reader ->
            println "Response status: ${resp.statusLine}"
            println "Response headers: ${resp.headers}"
            println "Response body:\n${resp.entity.content.text}"
        }
        
        response.failure = { resp, reader ->
            println "Request failed with status code ${resp.statusLine.statusCode}"
        }
    }
}

sendHttpRequest(url, method, headers, body)
