# DefaultApi

All URIs are relative to *http://127.0.0.1:8080/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**bank**](DefaultApi.md#bank) | **POST** /farkle/bank | 
[**getActualTurnPoints**](DefaultApi.md#getActualTurnPoints) | **GET** /farkle/actualTurnPoints | 
[**getCurrentPlayerID**](DefaultApi.md#getCurrentPlayerID) | **GET** /farkle/currentPlayerId | 
[**getDicesPlates**](DefaultApi.md#getDicesPlates) | **GET** /farkle/dicesPlate | 
[**getPlayer**](DefaultApi.md#getPlayer) | **GET** /farkle/player{id} | 
[**getSelectedDices**](DefaultApi.md#getSelectedDices) | **GET** /farkle/selectedDices | 
[**getState**](DefaultApi.md#getState) | **GET** /farkle/stateChanged | 
[**getWinner**](DefaultApi.md#getWinner) | **GET** /farkle/winner | 
[**name**](DefaultApi.md#name) | **POST** /farkle/name | 
[**quit**](DefaultApi.md#quit) | **POST** /farkle/quit | 
[**roll**](DefaultApi.md#roll) | **POST** /farkle/roll | 
[**select**](DefaultApi.md#select) | **POST** /farkle/select | 

<a name="bank"></a>
# **bank**
> String bank()



bank the current player

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    String result = apiInstance.bank();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#bank");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getActualTurnPoints"></a>
# **getActualTurnPoints**
> Integer getActualTurnPoints()



return the total of all selected dices in this turn

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    Integer result = apiInstance.getActualTurnPoints();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getActualTurnPoints");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCurrentPlayerID"></a>
# **getCurrentPlayerID**
> Integer getCurrentPlayerID()



return the current Player if game started, -1 if game not yet started

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    Integer result = apiInstance.getCurrentPlayerID();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getCurrentPlayerID");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getDicesPlates"></a>
# **getDicesPlates**
> RestDices getDicesPlates()



return the arrays of Dices on the Plate

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    RestDices result = apiInstance.getDicesPlates();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getDicesPlates");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**RestDices**](RestDices.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getPlayer"></a>
# **getPlayer**
> RestPlayer getPlayer(id)



return the asked Player

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer id = 56; // Integer | 
try {
    RestPlayer result = apiInstance.getPlayer(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getPlayer");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  |

### Return type

[**RestPlayer**](RestPlayer.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getSelectedDices"></a>
# **getSelectedDices**
> RestDices getSelectedDices()



return the arrays of Selected Dices

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    RestDices result = apiInstance.getSelectedDices();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getSelectedDices");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**RestDices**](RestDices.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getState"></a>
# **getState**
> Integer getState()



return 0 if nothing change, 1 if something append

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    Integer result = apiInstance.getState();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getState");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getWinner"></a>
# **getWinner**
> RestPlayer getWinner()



return the winning Player or error if game not yet finished

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    RestPlayer result = apiInstance.getWinner();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getWinner");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**RestPlayer**](RestPlayer.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="name"></a>
# **name**
> RestPlayer name(name)



log a player with a given name ; return the logged player if successfull. If more than two players return an error

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String name = "name_example"; // String | 
try {
    RestPlayer result = apiInstance.name(name);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#name");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**|  | [optional]

### Return type

[**RestPlayer**](RestPlayer.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="quit"></a>
# **quit**
> String quit(playerId)



logout a player and terminate the actual play

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer playerId = 56; // Integer | 
try {
    String result = apiInstance.quit(playerId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#quit");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **playerId** | **Integer**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="roll"></a>
# **roll**
> String roll()



roll the dices on the plate

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    String result = apiInstance.roll();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#roll");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="select"></a>
# **select**
> String select(dices)



select the given dices (e.g. 5 1 1)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String dices = "dices_example"; // String | 
try {
    String result = apiInstance.select(dices);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#select");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dices** | **String**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

