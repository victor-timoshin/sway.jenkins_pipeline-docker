#!/usr/bin/env groovy

import org.jenkinsci.plugins.scriptsecurity.scripts.*

void approveSignatures(ArrayList<String> signatures) {
    def scriptApproval = ScriptApproval.get()
    def alreadyApproved = new HashSet<>(Arrays.asList(scriptApproval.getApprovedSignatures()))
    signatures.each {
      if (!alreadyApproved.contains(it)) {
        scriptApproval.approveSignature(it)
      }
    }
}

String booleanToCMakeStr(Boolean val) {
  return (val) ? "ON" : "OFF"
}

return this
