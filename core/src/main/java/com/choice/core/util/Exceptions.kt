package com.choice.core.util

class NetworkCallException(message: String): Exception(message)
class UseCasesException(message: String) : Exception(message)
open class RepositoryException(message: String) : Exception(message)