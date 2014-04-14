# Trimet

A Clojure library that queries the [Trimet API](http://developer.trimet.org). It currently does very little.

## Usage

First, you'll need to [register for an AppID](http://developer.trimet.org/appid/registration/). Make a note of what it is; this library is useless without it.

The main entry point is the `new-stop-id-query-fn` function, which functions like a constructor (it's a closure, actually). Pass in your appID:

    (def myquery (new-stop-id-query-fn "MYAPPID"))

The `myquery` function takes one or more stop IDs and returns a map of the API response.

I'm working on some more functions to parse that response map, but for now there's just `next-arrivals`, which takes that map plus a route number and does what it says on the tin.

## License

Copyright © 2014 Justin Holguin

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
