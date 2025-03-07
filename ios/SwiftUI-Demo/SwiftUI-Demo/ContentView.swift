//
//  ContentView.swift
//  SwiftUI-Demo
//
//  Created by @BrainCoTech on 2025/3/7.
//

import SwiftUI
import TreadmillSDK

struct ContentView: View {
    // MARK: - Properties
    private let userId = "550e8400-e29b-41d4-a716-446655440000"
    private let snCode = "SN123456"
    private let originalText = "Hello, world!"
    
    // MARK: - Computed Properties
    private var encrypted: [UInt8] {
        return TreadmillCrypto.encrypt(
            plaintext: Array(originalText.utf8),
            userId: userId,
            snCode: snCode
        )
    }
    
    private var decrypted: [UInt8] {
        return TreadmillCrypto.decrypt(
            encrypted: encrypted,
            userId: userId,
            snCode: snCode
        )
    }
    
    // MARK: - Body
    var body: some View {
        VStack(spacing: 20) {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            
            // Original Text
            VStack(alignment: .leading) {
                Text("Original:")
                    .font(.headline)
                Text(originalText)
            }
            
            // Encrypted Data
            VStack(alignment: .leading) {
                Text("Encrypted:")
                    .font(.headline)
                Text(encrypted.map { String(format: "%02x", $0) }.joined())
                    .font(.system(.body, design: .monospaced))
            }
            
            // Decrypted Text
            VStack(alignment: .leading) {
                Text("Decrypted:")
                    .font(.headline)
                Text(String(bytes: decrypted, encoding: .utf8) ?? "Decryption failed")
            }
        }
        .padding()
        .frame(maxWidth: .infinity)
    }
}

// MARK: - Preview
#Preview {
    ContentView()
}
