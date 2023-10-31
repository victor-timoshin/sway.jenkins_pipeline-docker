#!/usr/bin/env groovy

import sway.jenkins_pipeline.docker.*

def call(body) {
    echo "Call image"

    def img = new MyImage("./", "default", "latest")
    script:this.echo(img.name())

    return this
}
