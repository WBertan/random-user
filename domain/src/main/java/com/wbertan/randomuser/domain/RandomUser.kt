package com.wbertan.randomuser.domain

class RandomUser {
  var gender: String? = null
  var name: Name? = null
  var location: Location? = null
  var email: String? = null
  var login: Login? = null
  var dob: String? = null
  var registered: String? = null
  var phone: String? = null
  var cell: String? = null
  var id: Id? = null
  var picture: Picture? = null
  var nat: String? = null

  class Id {
    var name: String? = null
    var value: String? = null
  }

  class Location {
    var street: String? = null
    var city: String? = null
    var state: String? = null
    var postcode: Int? = null
  }

  class Login {
    var username: String? = null
    var password: String? = null
    var salt: String? = null
    var md5: String? = null
    var sha1: String? = null
    var sha256: String? = null
  }

  class Name {
    var title: String? = null
    var first: String? = null
    var last: String? = null
  }

  class Picture {
    var large: String? = null
    var medium: String? = null
    var thumbnail: String? = null
  }
}